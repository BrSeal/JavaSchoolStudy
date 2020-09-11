insert into authorities (authority, username)
values
("ROLE_ADMIN","admin"),
("ROLE_USER","admin"),
("ROLE_EMPLOYEE","admin"),
("ROLE_DRIVER","admin"),
("ROLE_USER","employee"),
("ROLE_EMPLOYEE","employee"),
("ROLE_USER","driver"),
("ROLE_DRIVER","driver");

insert into users (username, password,enabled)
    values
    ("admin", "{noop}admin", 1),
    ("employee", "{noop}employee", 1),
    ("driver", "{noop}driver", 1);
