package actionsPages;

import navigation.Konsole;
import tables.Product;

public class SetQuantitePage implements simplePageInt {

    public void showPage() {
        Konsole.clearConsole();
        Konsole.showTitle("Modifier Quantité");

        System.out.println("Comment souhaitez-vous sélectionner le produit?");
        System.out.println("[1] Par ID");
        System.out.println("[2] Par titre");
        System.out.println("[0] Retour");

        int choice = Konsole.readUserInputInt();

        switch (choice) {
            case 0:
                Konsole.showNavigator();
                break;
            case 1:
                selectById();
                break;
            case 2:
                selectByTitle();
                break;
            default:
                showPage();
                break;
        }
    }

    private void selectById() {
        System.out.println("Entrez l'ID du produit:");
        int id = Konsole.readUserInputInt();
        Product product = Product.getProductById(id);

        if (product != null) {
            modifyQuantity(product);
        } else {
            System.out.println("Aucun produit trouvé avec cet ID.");
            Konsole.sleep(2000);
            showPage();
        }
    }

    private void selectByTitle() {
        System.out.println("Entrez le titre du produit (ou une partie):");
        String searchTerm = Konsole.readUserLine();

        Product product = Product.searchProductByTitle(searchTerm);

        if (product != null) {
            modifyQuantity(product);
        } else {
            System.out.println("Aucun produit trouvé avec ce titre.");
            Konsole.sleep(2000);
            showPage();
        }
    }

    private void modifyQuantity(Product product) {
        Konsole.clearConsole();
        System.out.println("Produit sélectionné:");
        System.out.println("ID: " + product.getId());
        System.out.println("Titre: " + product.getTitre());
        System.out.println("Quantité actuelle: " + product.getQuantite() + " " + product.getUnite());

        System.out.println("\nQue souhaitez-vous faire?");
        System.out.println("[1] Ajouter de la quantité");
        System.out.println("[2] Retirer de la quantité");
        System.out.println("[3] Définir une nouvelle quantité");
        System.out.println("[0] Annuler");

        int choice = Konsole.readUserInputInt();

        switch (choice) {
            case 0:
                showPage();
                break;
            case 1:
                addQuantity(product);
                break;
            case 2:
                removeQuantity(product);
                break;
            case 3:
                setQuantity(product);
                break;
            default:
                modifyQuantity(product);
                break;
        }
    }

    private void addQuantity(Product product) {
        System.out.println("Quantité actuelle: " + product.getQuantite() + " " + product.getUnite());
        System.out.println("Entrez la quantité à ajouter:");
        float amount = Konsole.readUserInputInt();

        float newQuantity = product.getQuantite() + amount;
        product.setQuantite(newQuantity);
        product.update();

        System.out.println("Quantité mise à jour: " + newQuantity + " " + product.getUnite());
        Konsole.sleep(2000);
        showPage();
    }

    private void removeQuantity(Product product) {
        System.out.println("Quantité actuelle: " + product.getQuantite() + " " + product.getUnite());
        System.out.println("Entrez la quantité à retirer:");
        float amount = Konsole.readUserInputInt();

        if (amount > product.getQuantite()) {
            System.out.println("Erreur: Vous ne pouvez pas retirer plus que la quantité disponible.");
            Konsole.sleep(2000);
            modifyQuantity(product);
            return;
        }

        float newQuantity = product.getQuantite() - amount;
        product.setQuantite(newQuantity);
        product.update();

        System.out.println("Quantité mise à jour: " + newQuantity + " " + product.getUnite());
        Konsole.sleep(2000);
        showPage();
    }

    private void setQuantity(Product product) {
        System.out.println("Quantité actuelle: " + product.getQuantite() + " " + product.getUnite());
        System.out.println("Entrez la nouvelle quantité:");
        float newQuantity = Konsole.readUserInputInt();

        product.setQuantite(newQuantity);
        product.update();

        System.out.println("Quantité mise à jour: " + newQuantity + " " + product.getUnite());
        Konsole.sleep(2000);
        showPage();
    }
}