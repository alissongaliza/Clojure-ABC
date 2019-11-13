(ns my-api.endpoints
  (:gen-class)
  (:require [compojure.api.sweet :refer [POST GET PUT]]
            [ring.util.http-response :refer :all]
            [my-api.schemas :refer [Ingredient Recipe]]
            [my-api.handler :refer [updateFridge createFridge getAllFridges]]))


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
