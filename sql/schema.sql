CREATE DATABASE bookmarks;

CREATE EXTENSION pgcrypto;

CREATE TABLE roles (
  id   UUID PRIMARY KEY,
  role VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users (
  id       UUID PRIMARY KEY,
  name     VARCHAR(255)        NOT NULL,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL
);

CREATE TABLE user_roles (
  id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);

CREATE TABLE images (
  id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  url  VARCHAR(500) NOT NULL
);

CREATE TABLE bookmarks (
  id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name     VARCHAR(255) NOT NULL,
  url      VARCHAR(500) NOT NULL,
  image_id UUID,
  user_id  UUID,

  FOREIGN KEY (image_id) REFERENCES images (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);