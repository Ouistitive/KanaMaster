CREATE TABLE Alphabet(
	sonorite VARCHAR(3),
	type VARCHAR(8)
);

CREATE TABLE Utilisateur(
	nomUtilisateur VARCHAR(20),
	motDePasse VARCHAR(50),
	meilleurScoreHiragana INTEGER,
	meilleurScoreKatakana INTEGER,
	meilleurScoreKana INTEGER
);

INSERT INTO Utilisateur(nomUtilisateur, motDePasse, meilleurScoreHiragana, meilleurScoreKatakana, meilleurScoreKana) VALUES
("Tony", "eee7ac208064d408e84ab5e26d24b278", 27, 32, 28),
("Etienne", "e3370a0bb2c2ea49f93b68c0649d57b6", 28, 16, 29),
("Noé", "22894f328ab8d094dd1b118351342958", 20, 30, 13),
("Armando", "b486dc5ce21f64603337b7b356c4c6d7", 27, 21, 17),
("Raphael", "84fc7720d5e7bf07115d91762843b8ad", 28, 22, 16),
("Ilo", "f13ec61dd34461e0c05be45d436b9732", 14, 24, 33),
("Aaron", "1c0a11cc4ddc0dbd3fa4d77232a4e22e", 20, 15, 22),
("Patrick", "f87567f2159b425795ebb7ba9bc406ec", 18, 23, 25),
("Steven", "c44e1acacdf5711ffa393d32636dc596", 26, 22, 25);


INSERT INTO Alphabet(sonorite, type) VALUES
('A', "Hiragana"),
('I', "Hiragana"),
('U', "Hiragana"),
('E', "Hiragana"),
('O', "Hiragana"),
('KA', "Hiragana"),
('KI', "Hiragana"),
('KU', "Hiragana"),
('KE', "Hiragana"),
('KO', "Hiragana"),
('SA', "Hiragana"),
('SHI', "Hiragana"),
('SU', "Hiragana"),
('SE', "Hiragana"),
('SO', "Hiragana"),
('TA', "Hiragana"),
('CHI', "Hiragana"),
('TSU', "Hiragana"),
('TE', "Hiragana"),
('TO', "Hiragana"),
('NA', "Hiragana"),
('NI', "Hiragana"),
('NU', "Hiragana"),
('NE', "Hiragana"),
('NO', "Hiragana"),
('HA', "Hiragana"),
('HI', "Hiragana"),
('FU', "Hiragana"),
('HE', "Hiragana"),
('HO', "Hiragana"),
('MA', "Hiragana"),
('MI', "Hiragana"),
('MU', "Hiragana"),
('ME', "Hiragana"),
('MO', "Hiragana"),
('YA', "Hiragana"),
('YU', "Hiragana"),
('YO', "Hiragana"),
('RA', "Hiragana"),
('RI', "Hiragana"),
('RU', "Hiragana"),
('RE', "Hiragana"),
('RO', "Hiragana"),
('WA', "Hiragana"),
('WO', "Hiragana"),
('N', "Hiragana"),

('A', "Katakana"),
('I', "Katakana"),
('U', "Katakana"),
('E', "Katakana"),
('O', "Katakana"),
('KA', "Katakana"),
('KI', "Katakana"),
('KU', "Katakana"),
('KE', "Katakana"),
('KO', "Katakana"),
('SA', "Katakana"),
('SHI', "Katakana"),
('SU', "Katakana"),
('SE', "Katakana"),
('SO', "Katakana"),
('TA', "Katakana"),
('CHI', "Katakana"),
('TSU', "Katakana"),
('TE', "Katakana"),
('TO', "Katakana"),
('NA', "Katakana"),
('NI', "Katakana"),
('NU', "Katakana"),
('NE', "Katakana"),
('NO', "Katakana"),
('HA', "Katakana"),
('HI', "Katakana"),
('FU', "Katakana"),
('HE', "Katakana"),
('HO', "Katakana"),
('MA', "Katakana"),
('MI', "Katakana"),
('MU', "Katakana"),
('ME', "Katakana"),
('MO', "Katakana"),
('YA', "Katakana"),
('YU', "Katakana"),
('YO', "Katakana"),
('RA', "Katakana"),
('RI', "Katakana"),
('RU', "Katakana"),
('RE', "Katakana"),
('RO', "Katakana"),
('WA', "Katakana"),
('WO', "Katakana"),
('N', "Katakana");