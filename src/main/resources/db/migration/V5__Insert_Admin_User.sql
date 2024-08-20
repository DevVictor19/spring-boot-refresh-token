insert into users (email, username, "password")
values ('admin@admin.com', 'admin', '$2a$10$Jne7l8ldB2uH0D8u71hzLeT0PAxuRHUoeuAOR6nqPdtnK5AtDCa52');

insert into user_permissions (user_id, permission_id)
values (1, 1);