CREATE TABLE customer (
  id UUID PRIMARY KEY,
  firstname VARCHAR(50),
  lastName VARCHAR(50),
  username VARCHAR(50),
  email VARCHAR(50),
  password VARCHAR(50),
  roles VARCHAR(100),
  enabled BOOLEAN,
  account_non_expired BOOLEAN,
  credentials_non_expired BOOLEAN,
  account_non_locked BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);