# BCrypt

* Хеширование пароля с солью

* Соль - случайная строка

```

$2a$[cost]$[22 character salt][31 character hash]

qwerty007

hash = hash(qwerty007 + salt)

password_hash = $2a$10$[salt][hash]

$2a$10$[N9qo8uLOickgx2ZMRZoMye][IjZAgcfl7p92ldGxad68LJZdL17lhWy]

```

* Проверка

```
login - marsel
password - qwerty007

Достаем полный хэш из базы у пользователя marsel

$2a$10$[N9qo8uLOickgx2ZMRZoMye][IjZAgcfl7p92ldGxad68LJZdL17lhWy]

salt = N9qo8uLOickgx2ZMRZoMye
hash = IjZAgcfl7p92ldGxad68LJZdL17lhWy
raw_password = qwerty007
```