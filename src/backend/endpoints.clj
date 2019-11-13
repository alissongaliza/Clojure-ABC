(ns backend.endpoints
  (:gen-class)
  (:require [compojure.api.sweet :refer [POST GET PUT]]
            [ring.util.http-response :refer :all]
            [backend.schemas :refer [Ingredient Recipe]]
            [backend.handler :refer [updateFridge createFridge getAllFridges]]))


(def user-routes
  [
   (POST "/fridge" []
     :body [ingredients [Ingredient]]
     (createFridge ingredients)
     )
   (PUT "/fridge/:id" [id]
     :body [ingredients [Ingredient]]
     (updateFridge ingredients id))
   (GET "/fridge" []
     (getAllFridges))
   ])
