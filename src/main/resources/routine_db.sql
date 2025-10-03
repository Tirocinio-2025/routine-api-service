CREATE TABLE IF NOT EXISTS routine
(
    routine_id   BIGINT AUTO_INCREMENT NOT NULL,
    nome_routine VARCHAR(255) NOT NULL,
    CONSTRAINT pk_routine PRIMARY KEY (routine_id)
);

CREATE TABLE IF NOT EXISTS alert
(
    alert_id       BIGINT AUTO_INCREMENT NOT NULL,
    ora_inizio     TIMESTAMP                               NOT NULL,
    ora_fine       TIMESTAMP                               NOT NULL,
    testo_notifica VARCHAR(100)                            NOT NULL,
    citta          VARCHAR(100)                            NOT NULL,
    routine_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_alert PRIMARY KEY (alert_id),
    CONSTRAINT FK_ALERT_ON_ROUTINE FOREIGN KEY (routine_id) REFERENCES routine (routine_id)
);

-- Creazione tabella weather_codes
CREATE TABLE weather_codes (
                               code INT PRIMARY KEY,         -- codice meteo univoco
                               day VARCHAR(100) NOT NULL,    -- descrizione giorno
                               night VARCHAR(100) NOT NULL,  -- descrizione notte
                               icon INT NOT NULL             -- id icona associata
);

INSERT INTO routine (routine_id, nome_routine) VALUES (1, 'Bongiorn eh');
INSERT INTO alert (alert_id, ora_inizio, ora_fine, testo_notifica, citta, routine_id) VALUES (1, '2024-07-01 07:00:00', '2024-07-01 08:00:00', 'Bongiorn eh.', 'Silvi', 1);
INSERT INTO weather_codes (code, day, night, icon) VALUES
                                                       (1000, 'Sunny', 'Clear', 113),
                                                       (1003, 'Partly cloudy', 'Partly cloudy', 116),
                                                       (1006, 'Cloudy', 'Cloudy', 119),
                                                       (1009, 'Overcast', 'Overcast', 122),
                                                       (1030, 'Mist', 'Mist', 143),
                                                       (1063, 'Patchy rain possible', 'Patchy rain possible', 176),
                                                       (1066, 'Patchy snow possible', 'Patchy snow possible', 179),
                                                       (1069, 'Patchy sleet possible', 'Patchy sleet possible', 182),
                                                       (1072, 'Patchy freezing drizzle possible', 'Patchy freezing drizzle possible', 185),
                                                       (1087, 'Thundery outbreaks possible', 'Thundery outbreaks possible', 200),
                                                       (1114, 'Blowing snow', 'Blowing snow', 227),
                                                       (1117, 'Blizzard', 'Blizzard', 230),
                                                       (1135, 'Fog', 'Fog', 248),
                                                       (1147, 'Freezing fog', 'Freezing fog', 260),
                                                       (1150, 'Patchy light drizzle', 'Patchy light drizzle', 263),
                                                       (1153, 'Light drizzle', 'Light drizzle', 266),
                                                       (1168, 'Freezing drizzle', 'Freezing drizzle', 281),
                                                       (1171, 'Heavy freezing drizzle', 'Heavy freezing drizzle', 284),
                                                       (1180, 'Patchy light rain', 'Patchy light rain', 293),
                                                       (1183, 'Light rain', 'Light rain', 296),
                                                       (1186, 'Moderate rain at times', 'Moderate rain at times', 299),
                                                       (1189, 'Moderate rain', 'Moderate rain', 302),
                                                       (1192, 'Heavy rain at times', 'Heavy rain at times', 305),
                                                       (1195, 'Heavy rain', 'Heavy rain', 308),
                                                       (1198, 'Light freezing rain', 'Light freezing rain', 311),
                                                       (1201, 'Moderate or heavy freezing rain', 'Moderate or heavy freezing rain', 314),
                                                       (1204, 'Light sleet', 'Light sleet', 317),
                                                       (1207, 'Moderate or heavy sleet', 'Moderate or heavy sleet', 320),
                                                       (1210, 'Patchy light snow', 'Patchy light snow', 323),
                                                       (1213, 'Light snow', 'Light snow', 326),
                                                       (1216, 'Patchy moderate snow', 'Patchy moderate snow', 329),
                                                       (1219, 'Moderate snow', 'Moderate snow', 332),
                                                       (1222, 'Patchy heavy snow', 'Patchy heavy snow', 335),
                                                       (1225, 'Heavy snow', 'Heavy snow', 338),
                                                       (1237, 'Ice pellets', 'Ice pellets', 350),
                                                       (1240, 'Light rain shower', 'Light rain shower', 353),
                                                       (1243, 'Moderate or heavy rain shower', 'Moderate or heavy rain shower', 356),
                                                       (1246, 'Torrential rain shower', 'Torrential rain shower', 359),
                                                       (1249, 'Light sleet showers', 'Light sleet showers', 362),
                                                       (1252, 'Moderate or heavy sleet showers', 'Moderate or heavy sleet showers', 365),
                                                       (1255, 'Light snow showers', 'Light snow showers', 368),
                                                       (1258, 'Moderate or heavy snow showers', 'Moderate or heavy snow showers', 371),
                                                       (1261, 'Light showers of ice pellets', 'Light showers of ice pellets', 374),
                                                       (1264, 'Moderate or heavy showers of ice pellets', 'Moderate or heavy showers of ice pellets', 377),
                                                       (1273, 'Patchy light rain with thunder', 'Patchy light rain with thunder', 386),
                                                       (1276, 'Moderate or heavy rain with thunder', 'Moderate or heavy rain with thunder', 389),
                                                       (1279, 'Patchy light snow with thunder', 'Patchy light snow with thunder', 392),
                                                       (1282, 'Moderate or heavy snow with thunder', 'Moderate or heavy snow with thunder', 395);
-- INSERT INTO condizioni_meteo (codice, tipo) VALUES (1, 'NUVOLOSO');