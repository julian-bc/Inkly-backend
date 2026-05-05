FROM node:25-alpine AS builder
# Install Git
RUN apk add --no-cache git
WORKDIR /source
# Clone repository
RUN git clone https://github.com/LiangCamilo/story-service.git .
# Install dependencies and generate dist code
RUN npm install
RUN npx prisma generate && npm run build

# Final Image
FROM node:25-alpine
WORKDIR /app
# Copy src code, package.json and prisma
COPY --from=builder /source/dist ./dist
COPY --from=builder /source/package*.json ./
COPY --from=builder /source/prisma ./prisma
# Install deppendencies without devDependencies
RUN npm install --omit=dev
# Database synchronization
RUN npx prisma migrate deploy

CMD ["node", "dist/src/main.js"]