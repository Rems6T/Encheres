<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>

  <body>
    <%@ include file="/WEB-INF/fragments/HeaderAutre.jsp"%>
    <main class="p-5">
      <div class="d-flex justify-content-center pb-5">
        <h1>Nouvelle vente</h1>
      </div>

      <!-- Ajouter un fichier NouvelleVente dans les controleurs -->

      <form action="<%=request.getContextPath()%>/NouvelleVente" method="post" class="container d-flex">

        <!-- Image séparée du reste -->
        <div class="col-4">
          <img
            src="test.jpg"
            alt="AS picture"
            style="width: 90%; height: auto"
          />
        </div>
        
        <!-- Partie formulaire -->
        <div class="col-9">
          <div class="row">
            <div class="col-5">
              <label for="nom">Article :</label>
            </div>
            <div class="col-7">
              <input type="text" placeholder="Nom de l'article" required />
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="description">Description :</label>
            </div>
            <div class="col-7">
              <input type="text" style="height: 4rem" />
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="categorie">Catégorie :</label>
            </div>
            <div class="col-7">
              <select name="categorie" id="categorie">
                <option value="toutes">Toutes</option>
                <option value="informatique">Informatique</option>
                <option value="">Categorie 2</option>
                <option value="">Categorie 3</option>
                <option value="sportsloisirs">Sports et loisirs</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="photo">Photo de l'article :</label>
            </div>
            <div class="col-7">
              <input
                type="file"
                name="photo"
                id="photo"
                accept="image/png, image/jpg, image/jpeg"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="">Mise à prix :</label>
            </div>
            <div class="col-7">
              <input type="number" min="0" />
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="">Début de l'enchère : </label>
            </div>
            <div class="col-7">
              <!-- Min et max a modifié sur demande -->
              <input
                type="date"
                id="date_start"
                name="date_start"
                value="2018-07-22"
                min="2022-12-23"
                max="2023-01-23"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-5">
              <label for="">Fin de l'enchère :</label>
            </div>
            <div class="col-7">
              <!-- Mettre une date minimum à 1 jour après la date de début de l'enchère -->
              <input
                type="date"
                id="date_start"
                name="date_start"
                value="2018-07-22"
                min="2022-12-23"
                max="2023-05-23"
              />
            </div>
          </div>

          <!-- Adresse de retrait -->

          <fieldset class="col-9">
            <legend>Adresse de retrait</legend>
            <hr />
            <div class="row">
              <div class="col-5">
                <label for="nom">Article :</label>
              </div>
              <div class="col-7">
                <input type="text" required />
              </div>
            </div>
            <div class="row">
              <div class="col-5">
                <label for="nom">Article :</label>
              </div>
              <div class="col-7">
                <input type="text" required />
              </div>
            </div>
            <div class="row">
              <div class="col-5">
                <label for="nom">Article :</label>
              </div>
              <div class="col-7">
                <input type="text" required />
              </div>
            </div>
          </fieldset>

          <div class="row">
            <div id="buttons">
              <button type="submit" value="enregistrerArticle">
                Enregistrer
              </button>

              <a href="<%=request.getContextPath()%>/Accueil.jsp">
                <button type="button">Annuler</button>
              </a>
            </div>
          </div>

        </div>
      </form>
    </main>

  </body>
  
  <jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>
