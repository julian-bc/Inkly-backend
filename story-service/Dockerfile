FROM node:24-alpine AS builder
# Install Git
RUN apk add --no-cache git

WORKDIR /repo
# Clone repository
RUN git clone --depth 1 https://github.com/LiangCamilo/story-service.git .

WORKDIR /app

RUN cp -a /repo/. /app/
# Install dependencies and generate dist code
RUN npm ci
RUN npx prisma generate --config ./prisma.config.ts
RUN npm run build

RUN npm prune --omit=dev
# Final Image
FROM node:24-alpine AS runner

RUN apk add --no-cache openssl

WORKDIR /app
# Copy src code, package.json and prisma
COPY --from=builder --chown=node:node /app/package*.json ./
COPY --from=builder --chown=node:node /app/node_modules ./node_modules
COPY --from=builder --chown=node:node /app/dist ./dist
COPY --from=builder --chown=node:node /app/prisma ./prisma
COPY --from=builder --chown=node:node /app/prisma.config.ts ./prisma.config.ts

CMD ["sh", "-c", "npx prisma migrate deploy --config ./prisma.config.ts && node dist/src/main.js"]
