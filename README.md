# Telegram Update Gateway

A bridge between Telegram and the Tensai CMS that runs the **[@tensai_cms_bot](https://t.me/tensai_cms_bot)** — a bot that manages content inside Telegram Forum groups.

The service has one job: **talk on behalf of two systems that don't understand each other.**

- **From Telegram:** it receives updates, turns them into clean events, and sends them to the CMS.
- **From the CMS:** it receives commands, builds the right Telegram API request, and sends it to Telegram.

## How it works

### Telegram → CMS

1. Telegram sends an update to the webhook endpoint (`POST /telegram/webhook`).
2. The service figures out what happened — a member joined, a topic was created or renamed, a message was sent or edited, or a button was pressed.
3. It converts the update into a simple event the CMS understands.
4. It forwards the event to the CMS.

Some side behaviors:

- When the bot is added to a group, it checks that it has admin rights (including the right to delete messages). If not, it asks the group to grant them.
- It also checks that Topics are enabled in the group. If not, it tells the group to enable them.
- A topic rename that only changes the emoji (not the name) is ignored.

### CMS → Telegram

1. The CMS sends a command to the internal endpoint (`POST /internal/command`).
2. The service validates the command and builds the matching Telegram API request — including inline keyboards when needed.
3. The request is sent to Telegram.

The service can also fetch and return files: `GET /internal/file/{fileId}` gets a file from Telegram and streams it back.

## Events sent to the CMS

| Event | When it happens |
| --- | --- |
| `REGISTER_USER` | The bot is made admin of a group |
| `CREATE_TOPIC` | A new topic is created in a Forum group |
| `UPDATE_TOPIC` | A topic title is changed |
| `CREATE_MESSAGE` | A new message is posted |
| `UPDATE_MESSAGE` | A message is edited |
| `CALLBACK_QUERY` | An inline button is pressed |

## Commands from the CMS

- **Send message** — with an optional inline keyboard
- **Delete message**
- **Delete topic**
- **Edit keyboard** — replace the keyboard on an existing message
- **Answer callback** — dismiss the loading state of a pressed button

Inline buttons come in three kinds:

- **Callback** — sends data back to the CMS
- **URL** — opens a link
- **Copy text** — copies text to the clipboard

### The `@NullUnless` validation

The custom validation annotation **`@NullUnless`** conditionally enforces that a field must be `null` based on the value of another field within the same class or record.

But using a single attribute as a discriminator (tag) for all fields could simulate the Discriminated Union (Tagged Union) pattern and enforce the rule:
> *Each field may only be filled when a "discriminator" field equals a specific value — and must be null otherwise.*
 
Example: when `command_type` is `SEND_MESSAGE`, only `send_message` may be filled in. All other payloads (`delete_message`, `delete_topic`, `edit_keyboard`, `answer_callback`) must be null.

If the wrong payload is present — or the right one is missing — validation fails with a clear error pointing at the exact field.

### Security (authentication)

There are no logins or sessions. Access is granted through **secret tokens sent in HTTP headers** validated by custom authentication filters. Each side of the gateway has its own key:

- **Telegram calls** to `/telegram/**` are accepted only when the `X-Telegram-Bot-Api-Secret-Token` header matches the webhook secret — the same token you set when registering the webhook with Telegram.
- **CMS calls** to `/internal/**` are accepted only when the `X-Internal-Secret` header matches the internal API key.

A request with a missing or wrong token gets a **401 Unauthorized** response. The same internal key is also attached automatically to every request the service sends to the CMS, so both sides can trust each other.

## Tech stack

- Java 25 + Spring Boot 4.1
- Spring Web (REST + `RestClient`)
- Spring Security (header-token auth)
- Bean Validation (including `@NullUnless`)
- Lombok
- Maven

## Configuration

Secrets come from environment variables:

| Variable | What it's for |
| --- | --- |
| `TELEGRAM_BOT_TOKEN` | The bot's API token |
| `TELEGRAM_WEBHOOK_SECRET` | The secret Telegram sends with each webhook call |
| `CMS_BASE_URL` | The CMS service address |
| `CMS_API_KEY` | The shared key between this service and the CMS |

## Project layout

```
src/main/java/com/tensai/telegram/
├── controller/     # REST endpoints
├── service/        # Business logic + HTTP clients
│   ├── webhook/         # Handles incoming updates
│   ├── update_handler/  # Routes updates by type
│   ├── cms_client/      # Sends events to the CMS
│   ├── command_handler/ # Routes CMS commands
│   ├── telegram_client/ # Calls the Telegram API
│   └── internal/        # Internal endpoint logic
├── mapper/         # Converts data between formats
├── dto/            # Data models (events, commands, webhook, API)
├── config/         # Security filters & HTTP client setup
├── validation/     # Custom @NullUnless validator
└── exception/      # Centralized error handling
```