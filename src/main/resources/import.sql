-- Benutzer
INSERT INTO benutzer(id, created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (1, now(), 'ADMIN', 'ADMIN', '', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
-- Rollen
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (1, now(), 'ADMIN');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (2, now(), 'SUPERVISOR');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (3, now(), 'BENUTZER');