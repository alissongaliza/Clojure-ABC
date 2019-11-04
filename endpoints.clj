(ns my-api.endpoints
  (:gen-class)
  (:require [compojure.api.sweet :refer [POST GET PUT]]
            [ring.util.http-response :refer :all]
            [my-api.schemas :refer [Ingredient Recipe]]
            [my-api.handler :refer [updateFridge]]))


(def user-routes
  [
   (POST "/fridge" []
     :body [ingredients [Ingredient]]
     )
   (PUT "/fridge/" []
     :body [ingredients [Ingredient]]
     (updateFridge ingredients))
   (GET "/")
   ])
