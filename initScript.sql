insert into users (username, password,enabled)
    value
    ("admin", "{noop}admin", 1);
insert into authorities (authority, username)
values
("ROLE_ADMIN","admin"),
("ROLE_USER","admin"),
("ROLE_EMPLOYEE","admin"),
("ROLE_DRIVER","admin");

insert into cities (name)
values
("Nazin"),
("Choldan"),
("Meklon");
