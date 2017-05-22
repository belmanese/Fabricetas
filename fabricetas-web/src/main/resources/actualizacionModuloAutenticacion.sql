ALTER TABLE fabricetas.user ADD PASSWORD varchar(100);
ALTER TABLE fabricetas.user ADD USUARIO varchar(100);
UPDATE fabricetas.user SET usuario = lower(FIRST_NAME) WHERE usuario is null;
UPDATE fabricetas.user SET password = usuario WHERE password is null;