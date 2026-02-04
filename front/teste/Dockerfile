# Estágio de Build - Atualizado para Node 20 para suportar o Vite 7
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Servidor de Produção (Nginx)
FROM nginx:stable-alpine
# Correção: O Vite gera os arquivos na pasta 'dist', não na 'build'
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]