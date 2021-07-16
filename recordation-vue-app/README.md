# Motifi

This is an application that helps individuals create budgets and set goals for savings. This is the frontend built with vue for this API: https://github.com/andremoresco/motifi

## Tech Stack

**Client:** Vue.js, Vedux, TypeScript, SCSS

**[Server](https://github.com/andremoresco/motifi):** Node, Express, TypeScript

## Environment Variables

To begin using running the project, first copy the example variables into either .env file:

- `cp .env.example .env.development.local` - for development
- `cp .env.example .env.production.local` - for production

You will need to modify the following environment variables in your .env file for your app to run:

`API_BASE_URL`

## Run Locally

Clone the project

```bash
  git clone https://github.com/PeterKitonga/motifivue.git
```

Go to the project directory

```bash
  cd motifivue
```

Install dependencies

```bash
  npm install
```

Start the development server

```bash
  npm run serve
```

## Deployment

To deploy this project run

```bash
  npm run build
```

## Running Tests

To run tests, run the following command

```bash
  npm run test:unit
```

## Linting

To lint the project files, run

```bash
  npm run lint
```

## Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).

## Authors

[@PeterKitonga](https://www.github.com/PeterKitonga)
[@AndreMoresco](https://www.github.com/AndreMoresco)
