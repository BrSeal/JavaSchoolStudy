insert into users (username, password,enabled)
    value
    ("admin", "$2y$10$APT3SKLf4C2DqV6vLVZoauw1gSMZ5.lYYyUe72dsNFOWp4dSKYh7q", 1);
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
