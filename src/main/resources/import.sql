-- Verpflichtend beim Hochfahren
-- Benutzer und Rollen
INSERT INTO benutzer(id, created_at, anzeigename, benutzername, dienstnummer, passwort) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'ADMIN', 'ADMIN', '', '$2a$10$eoJXaB5sKquqydjBbkxiHOIT7iowKUI6A2HUfpyYTrwaibJ5SfPFW');
-- Rollen
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'ADMIN');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'SUPERVISOR');
INSERT INTO rolle(id, created_at, bezeichnung) VALUES (nextval('benutzer_idbenutzer_seq'), now(), 'BENUTZER');
-- Benutzer und Rollen
-- Verpflichtend beim Hochfahren

-- Testdaten für JUnit Tests
INSERT INTO lagerstandort(id, benutzer) VALUES(1, 1);
INSERT INTO material(id, bestand, einsatzbereitschaft, lagerstandort, einkaufsdatum) VALUES(1, 1, true, 1, '2018-09-02');
INSERT INTO materialtyp(id, name) VALUES(1, 'Schraube');
-- Testdaten für JUnit Tests