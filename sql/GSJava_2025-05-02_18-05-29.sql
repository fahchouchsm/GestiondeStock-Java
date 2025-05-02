CREATE TABLE
  `categorie_products` (
    `idCategorie` int NOT NULL,
    `idProduit` int NOT NULL,
    PRIMARY KEY (`idCategorie`, `idProduit`),
    KEY `idProduit` (`idProduit`),
    CONSTRAINT `categorie_article_ibfk_1` FOREIGN KEY (`idCategorie`) REFERENCES `categories` (`idCategorie`) ON DELETE CASCADE,
    CONSTRAINT `categorie_article_ibfk_2` FOREIGN KEY (`idProduit`) REFERENCES `products` (`idProduit`) ON DELETE CASCADE
  )
CREATE TABLE
  `categories` (
    `idCategorie` int NOT NULL AUTO_INCREMENT,
    `nom` varchar(255) CHARACTER
    SET
      utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
      `description` text CHARACTER
    SET
      utf8mb4 COLLATE utf8mb4_0900_ai_ci,
      PRIMARY KEY (`idCategorie`),
      UNIQUE KEY `nom` (`nom`)
  )
CREATE TABLE
  `products` (
    `idProduit` int NOT NULL AUTO_INCREMENT,
    `titre` varchar(255) CHARACTER
    SET
      utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
      `quantite` decimal(10, 2) DEFAULT '0.00',
      `unite` varchar(50) CHARACTER
    SET
      utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
      `seuil` float DEFAULT NULL,
      `prixAchat` decimal(10, 2) DEFAULT NULL,
      `prixUnitaire` decimal(10, 2) NOT NULL,
      PRIMARY KEY (`idProduit`)
  )