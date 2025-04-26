CREATE TABLE
    `articles` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `nom` VARCHAR(255) NOT NULL,
        `quantite` DECIMAL(10, 2) NOT NULL DEFAULT 0,
        `unite` VARCHAR(50) DEFAULT NULL,
        `seuil` INT DEFAULT NULL,
        `prixAchat` DECIMAL(10, 2) NOT NULL,
        `prixUnitaire` DECIMAL(10, 2) NOT NULL,
        PRIMARY KEY (`id`)
    );

CREATE TABLE
    `categories` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `nom` VARCHAR(255) NOT NULL,
        `slug` VARCHAR(255) UNIQUE,
        `description` TEXT,
        PRIMARY KEY (`id`),
        UNIQUE KEY `nom` (`nom`)
    );

CREATE TABLE
    `categorie_article` (
        `idCategorie` INT NOT NULL,
        `idArticle` INT NOT NULL,
        PRIMARY KEY (`idCategorie`, `idArticle`),
        KEY `idArticle` (`idArticle`),
        FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`id`) ON DELETE CASCADE,
        FOREIGN KEY (`idArticle`) REFERENCES `articles` (`id`) ON DELETE CASCADE
    );

CREATE TABLE
    `repas` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `nom` VARCHAR(255) NOT NULL,
        `prix` DECIMAL(10, 2) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `nom` (`nom`)
    );

CREATE TABLE
    `ingredient` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `nom` VARCHAR(255) NOT NULL,
        `description` TEXT,
        `createdAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`),
        UNIQUE KEY `nom` (`nom`)
    );

CREATE TABLE
    `repas_ingredient` (
        `idRepas` INT NOT NULL,
        `idIngredient` INT NOT NULL, -- Changed to reference `ingredient`
        `quantite` DECIMAL(10, 2) NOT NULL,
        PRIMARY KEY (`idRepas`, `idIngredient`),
        KEY `idIngredient` (`idIngredient`), -- Changed from `idArticle` to `idIngredient`
        FOREIGN KEY (`idRepas`) REFERENCES `repas` (`id`) ON DELETE CASCADE,
        FOREIGN KEY (`idIngredient`) REFERENCES `ingredient` (`id`) ON DELETE CASCADE -- Now references `ingredient`
    );