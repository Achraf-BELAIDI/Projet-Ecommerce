# ALTEN SHOP FRONT

Launch the front-end with `ng serve` or `npm start`.


Documentation d'Utilisation

Les contrôleurs dans cette application permettent de gérer l'authentification des utilisateurs, la gestion du panier d'achat, les produits, les utilisateurs et la liste de souhaits. 

1. `AuthController`** - Gestion de l'authentification des utilisateurs
Le contrôleur `AuthController` gère la création de comptes et la connexion des utilisateurs.

- Route `POST /auth/create` : Crée un nouvel utilisateur
    - Paramètre** : `UserRequestDto` (données de l'utilisateur, telles que l'email et le mot de passe).
    - Réponse** : `JwtResponse` (un token JWT si la création réussit, ou un message d'erreur si l'utilisateur existe déjà).
  
- Route `POST /auth/login`** : Permet à un utilisateur de se connecter.
    - Paramètre : `JwtRequest` (contient l'email et le mot de passe).
    - Réponse : `JwtResponse` (un token JWT si la connexion réussit).

2. `CartController` - Gestion du panier
Le contrôleur `CartController` gère les produits ajoutés au panier par l'utilisateur.

- Route `POST /api/cart/add`** : Ajoute un produit au panier
    - Paramètre : `Product` (le produit à ajouter au panier).
    - Réponse : `CartItem` (l'élément ajouté au panier).

- Route `DELETE /api/cart/remove/{productId}`** : Supprime un produit du panier
    - Paramètre : `productId` (ID du produit à retirer).
    - **Réponse : Statut HTTP 204 (Pas de contenu) si l'opération réussit.

- Route `GET /api/cart/view` : Affiche les éléments du panier
    - Réponse : Le panier de l'utilisateur (détails des produits ajoutés).

- Route `GET /api/cart/quantity` : Affiche la quantité totale d'articles dans le panier.
    - Réponse : Un entier indiquant le nombre total d'articles dans le panier.

3. `CartItemController` - Gestion des éléments du panier
Ce contrôleur permet de gérer les éléments individuels dans le panier.

- Route `GET /api/cart-items/{cartId}` : Récupère les articles d'un panier spécifique.
    - Réponse : Liste des articles du panier.

- Route `PUT /api/cart-items/{cartItemId}` : Met à jour la quantité d'un article dans le panier.
    - Paramètre : `cartItemId` (ID de l'élément du panier) et `quantity` (nouvelle quantité).
    - Réponse : Message de succès si l'opération réussit.

- Route `DELETE /api/cart-items/{cartItemId}` : Supprime un article du panier.
    - Réponse : Message de succès si l'opération réussit.

4. `ContactController` - Gestion du formulaire de contact
Le contrôleur `ContactController` permet à un utilisateur de soumettre un formulaire de contact.

- Route `POST /api/contact` : Soumet un formulaire de contact.
    - Paramètre : `Contact` (données du formulaire).
    - Réponse : L'objet `Contact` sauvegardé ou une erreur si l'opération échoue.

5. `ProductController` - Gestion des produits
Le contrôleur `ProductController` permet de créer, récupérer, mettre à jour et supprimer des produits.

- Route `POST /api/products` : Crée un produit (uniquement pour l'administrateur).
    - Paramètre : `Product` (les informations du produit).
    - Réponse: Produit créé si l'utilisateur est un administrateur.

- Route `GET /api/products` : Récupère tous les produits.
    - Réponse : Liste de tous les produits.

- Route `GET /api/products/{id}` : Récupère un produit par son ID.
    - Réponse : Le produit correspondant ou une erreur si non trouvé.

- Route `PUT /api/products/{id}` : Met à jour un produit.
    - Paramètre : `id` (ID du produit), `Product` (nouveaux détails du produit).
    - Réponse : Le produit mis à jour si l'utilisateur est un administrateur.

- Route `DELETE /api/products/{id}`** : Supprime un produit.
    - Réponse : Statut HTTP 204 si le produit est supprimé avec succès.

6. `UserController` - Gestion des utilisateurs
Le contrôleur `UserController` permet de gérer les informations des utilisateurs.

- Route `GET /user` : Récupère la liste de tous les utilisateurs.
    - Réponse : Liste des utilisateurs.

- Route `POST /user/register`** : Enregistre un nouvel utilisateur.
    - Paramètre : `UserRequestDto` (données de l'utilisateur).
    - Réponse : Détails de l'utilisateur créé ou une erreur si l'enregistrement échoue.

7. `WishlistController` - Gestion de la liste de souhaits

Le contrôleur `WishlistController` permet de gérer les produits ajoutés à la liste de souhaits d'un utilisateur.

- Route `POST /api/wishlist/addOrRemove`** : Ajoute ou retire un produit de la liste de souhaits.
    - Paramètre : `Product` (le produit à ajouter ou retirer).
    - Réponse : La liste de souhaits mise à jour.

- Route `GET /api/wishlist/view` : Affiche les produits dans la liste de souhaits.
    - Réponse : La liste de souhaits de l'utilisateur.


Sécurisation et Accès
- L'accès aux routes sensibles (comme l'ajout, la modification ou la suppression de produits) est restreint aux administrateurs. Cela se fait en vérifiant l'email de l'utilisateur authentifié via `SecurityContextHolder`.

Erreurs courantes
- 403 Forbidden** : L'utilisateur tente d'accéder à une ressource qu'il n'est pas autorisé à utiliser.
- 404 Not Found** : L'élément demandé n'a pas été trouvé dans la base de données.
- 409 Conflict** : L'utilisateur tente de créer un compte avec un email déjà utilisé.


Ces contrôleurs permettent une gestion complète des fonctionnalités de l'application e-commerce, y compris l'authentification des utilisateurs, la gestion des produits, des paniers, des listes de souhaits et des formulaires de contact. Les administrateurs disposent de privilèges supplémentaires pour gérer les produits, tandis que les utilisateurs peuvent interagir avec leur panier et leurs listes de souhaits.


Pour chaque contrôleur, voici une documentation concise décrivant les fonctionnalités principales afin de guider les utilisateurs sur leur utilisation.

---

## **1. AuthController**
### Endpoint de gestion d'authentification et de création de comptes utilisateurs.
- **Base URL**: `/auth`

#### **Endpoints :**
1. **Créer un utilisateur**
   - **URL**: `/create`
   - **Méthode**: POST
   - **Entrée**: `UserRequestDto` (JSON contenant les détails de l'utilisateur)
   - **Réponse**: `JwtResponse` (JWT généré)
   - **Statuts HTTP**: 
     - 201: Créé avec succès.
     - 409: Conflit (l'utilisateur existe déjà).

2. **Connexion**
   - **URL**: `/login`
   - **Méthode**: POST
   - **Entrée**: `JwtRequest` (email et mot de passe).
   - **Réponse**: `JwtResponse` (JWT généré).
   - **Statuts HTTP**:
     - 200: Succès.
     - 401: Identifiants incorrects.

---

## **2. CartController**
### Endpoint pour gérer le panier des utilisateurs connectés.
- **Base URL**: `/api/cart`

#### **Endpoints :**
1. **Ajouter un produit au panier**
   - **URL**: `/add`
   - **Méthode**: POST
   - **Entrée**: `Product` (ID du produit à ajouter).
   - **Réponse**: `CartItem` (détail du produit ajouté).
   - **Statuts HTTP**: 
     - 201: Créé.
   
2. **Supprimer un produit du panier**
   - **URL**: `/remove/{productId}`
   - **Méthode**: DELETE
   - **Réponse**: Aucun contenu.
   - **Statuts HTTP**: 
     - 204: Succès.

3. **Voir le contenu du panier**
   - **URL**: `/view`
   - **Méthode**: GET
   - **Réponse**: `Cart` (contenu complet du panier).

4. **Obtenir la quantité totale dans le panier**
   - **URL**: `/quantity`
   - **Méthode**: GET
   - **Réponse**: `Integer` (quantité totale).

---

## **3. CartItemController**
### Endpoint pour gérer les éléments individuels dans un panier.
- **Base URL**: `/api/cart-items`

#### **Endpoints :**
1. **Récupérer les articles d'un panier**
   - **URL**: `/{cartId}`
   - **Méthode**: GET
   - **Réponse**: Liste de `CartItem`.

2. **Mettre à jour la quantité d'un article**
   - **URL**: `/{cartItemId}`
   - **Méthode**: PUT
   - **Paramètre**: `quantity` (nouvelle quantité).
   - **Réponse**: Confirmation.

3. **Supprimer un article d'un panier**
   - **URL**: `/{cartItemId}`
   - **Méthode**: DELETE
   - **Réponse**: Confirmation.

---

## **4. ContactController**
### Endpoint pour gérer les formulaires de contact.
- **Base URL**: `/api/contact`

#### **Endpoints :**
1. **Soumettre un formulaire de contact**
   - **URL**: `/`
   - **Méthode**: POST
   - **Entrée**: `Contact` (nom, email, message).
   - **Réponse**: `Contact` sauvegardé.

---

## **5. ProductController**
### Endpoint pour gérer les produits.
- **Base URL**: `/api`

#### **Endpoints :**
1. **Créer un produit** *(Administrateur uniquement)* 
   - **URL**: `/products`
   - **Méthode**: POST
   - **Entrée**: `Product` (détail du produit).
   - **Réponse**: Produit créé ou erreur d'accès.

2. **Afficher tous les produits**
   - **URL**: `/products`
   - **Méthode**: GET
   - **Réponse**: Liste de `Product`.

3. **Afficher un produit spécifique**
   - **URL**: `/products/{id}`
   - **Méthode**: GET
   - **Réponse**: Détail du produit ou 404 si introuvable.

4. **Mettre à jour un produit** *(Administrateur uniquement)* 
   - **URL**: `/products/{id}`
   - **Méthode**: PUT
   - **Entrée**: `Product` (détails mis à jour).
   - **Réponse**: Produit mis à jour.

5. **Supprimer un produit** *(Administrateur uniquement)* 
   - **URL**: `/products/{id}`
   - **Méthode**: DELETE
   - **Réponse**: Aucun contenu.

---

## **6. UserController**
### Endpoint pour gérer les utilisateurs.
- **Base URL**: `/user`

#### **Endpoints :**
1. **Récupérer tous les utilisateurs**
   - **URL**: `/`
   - **Méthode**: GET
   - **Réponse**: Liste de `UserResponseDto`.

2. **Enregistrer un nouvel utilisateur**
   - **URL**: `/register`
   - **Méthode**: POST
   - **Entrée**: `UserRequestDto` (détails de l'utilisateur).
   - **Réponse**: `UserResponseDto` ou erreur.

---

## **7. WishlistController**
### Endpoint pour gérer la liste d'envies des utilisateurs.
- **Base URL**: `/api/wishlist`

#### **Endpoints :**
1. **Ajouter ou retirer un produit de la liste d'envies**
   - **URL**: `/addOrRemove`
   - **Méthode**: POST
   - **Entrée**: `Product` (ID du produit).
   - **Réponse**: Liste mise à jour.

2. **Voir la liste d'envies**
   - **URL**: `/view`
   - **Méthode**: GET
   - **Réponse**: `Wishlist`.



Voici une documentation détaillée des **contrôleurs** de votre application, avec des descriptions de chaque endpoint, les URL associées, et une mention de Swagger pour la génération automatique de documentation. 

---

## **Documentation de l'API avec Swagger**



cette application utilise Swagger pour générer automatiquement une documentation interactive de l'API REST. Les utilisateurs peuvent accéder à l'interface Swagger via l'URL suivante :  
- **URL de Swagger** : `/swagger-ui.html` ou `/api-docs`

Cette interface permet de visualiser tous les endpoints, leurs paramètres, réponses, et d'effectuer des tests directement depuis le navigateur.

---

### **1. AuthController**
Gère l'authentification et la gestion des utilisateurs. 

**Base URL** : `/auth`

| **Méthode** | **URL**          | **Description**                                                                                   |
|-------------|------------------|---------------------------------------------------------------------------------------------------|
| POST        | `/auth/create`   | Crée un nouvel utilisateur et retourne un token JWT.                                             |
| POST        | `/auth/login`    | Authentifie un utilisateur existant et retourne un token JWT.                                    |

- **Exemple d'utilisation avec Swagger** :
  - Lors de la création d'un utilisateur, fournissez les données de l'utilisateur dans le corps de la requête (format JSON).
  - Le token JWT est ensuite utilisé pour sécuriser d'autres requêtes.

---

### **2. CartController**
Gère le panier d'achat des utilisateurs.

**Base URL** : `/api/cart`

| **Méthode** | **URL**                | **Description**                                                                                     |
|-------------|------------------------|-----------------------------------------------------------------------------------------------------|
| POST        | `/api/cart/add`        | Ajoute un produit au panier de l'utilisateur connecté.                                             |
| DELETE      | `/api/cart/remove/{id}`| Supprime un produit du panier de l'utilisateur connecté (via l'ID du produit).                     |
| GET         | `/api/cart/view`       | Retourne les détails du panier de l'utilisateur connecté.                                          |
| GET         | `/api/cart/quantity`   | Retourne la quantité totale des produits dans le panier de l'utilisateur connecté.                 |

---

### **3. CartItemController**
Permet de gérer les éléments spécifiques du panier.

**Base URL** : `/api/cart-items`

| **Méthode** | **URL**                         | **Description**                                                             |
|-------------|---------------------------------|-----------------------------------------------------------------------------|
| GET         | `/api/cart-items/{cartId}`      | Récupère tous les articles d'un panier spécifique (via l'ID du panier).     |
| PUT         | `/api/cart-items/{cartItemId}`  | Met à jour la quantité d'un article dans le panier (via l'ID de l'article). |
| DELETE      | `/api/cart-items/{cartItemId}`  | Supprime un article spécifique du panier (via l'ID de l'article).           |

---

### **4. ContactController**
Permet de gérer les demandes de contact via un formulaire.

**Base URL** : `/api/contact`

| **Méthode** | **URL**        | **Description**                          |
|-------------|----------------|------------------------------------------|
| POST        | `/api/contact` | Soumet une demande via le formulaire de contact. |

---

### **5. ProductController**
Gère les produits disponibles sur la plateforme.

**Base URL** : `/api/products`

| **Méthode** | **URL**                   | **Description**                                                                                          |
|-------------|---------------------------|----------------------------------------------------------------------------------------------------------|
| POST        | `/api/products`           | Crée un nouveau produit (autorisé uniquement pour un administrateur).                                   |
| GET         | `/api/products`           | Récupère la liste de tous les produits.                                                                 |
| GET         | `/api/products/{id}`      | Récupère les détails d'un produit spécifique (via l'ID).                                                |
| PUT         | `/api/products/{id}`      | Met à jour un produit existant (autorisé uniquement pour un administrateur).                            |
| DELETE      | `/api/products/{id}`      | Supprime un produit spécifique (autorisé uniquement pour un administrateur).                            |

---

### **6. UserController**
Gère la gestion des utilisateurs.

**Base URL** : `/user`

| **Méthode** | **URL**         | **Description**                                  |
|-------------|-----------------|--------------------------------------------------|
| GET         | `/user`         | Récupère la liste de tous les utilisateurs.      |
| POST        | `/user/register`| Enregistre un nouvel utilisateur.                |

---

### **7. WishlistController**
Gère la liste d'envies des utilisateurs.

**Base URL** : `/api/wishlist`

| **Méthode** | **URL**                     | **Description**                                                                 |
|-------------|-----------------------------|---------------------------------------------------------------------------------|
| POST        | `/api/wishlist/addOrRemove` | Ajoute ou supprime un produit de la liste d'envies de l'utilisateur connecté.   |
| GET         | `/api/wishlist/view`        | Récupère la liste d'envies de l'utilisateur connecté.                           |

---

### Points importants :
- **Sécurité** : 
  - Les endpoints sont sécurisés avec **JWT**, et l'utilisateur doit fournir un token valide dans l'en-tête `Authorization` pour accéder à la majorité des endpoints.
  - Exemple d'en-tête :  
    ```
    Authorization: Bearer <token>
    ```

- **Test avec Swagger** : 
  - Swagger facilite le test des endpoints en générant des exemples d'appels.
  - Ajoutez des annotations Swagger dans vos contrôleurs pour enrichir la documentation :
    ```java
    @Operation(summary = "Ajouter un produit au panier", description = "Ajoute un produit au panier de l'utilisateur connecté.")
    @PostMapping("/add")
    public ResponseEntity<CartItem> addProductToCart(@RequestBody Product product) {
        ...
    }
    ```

--- 

**URL pour accéder à Swagger** : `/swagger-ui/index.html`  
**URL pour accéder aux spécifications JSON de l'API** : `/v3/api-docs` 
