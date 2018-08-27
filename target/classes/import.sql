-- Benutzer
INSERT INTO benutzer(id, created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'ADMIN', 'ADMIN', '', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
-- Rollen
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'ADMIN');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'SUPERVISOR');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'BENUTZER');
