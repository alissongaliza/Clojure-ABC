(ns backend.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.http-response :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [backend.schemas :refer [Ingredient Recipe Fridge]]
            [clj-http.client :as client]
            [clojure.string :refer [join]])
  (:import org.bson.types.ObjectId))


(defn createFridge [ingredients]
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        uniqueID (ObjectId.)
        fridgeIngredients {:ingredients ingredients}
        collection "fridges"]
   (mc/insert db collection (merge fridgeIngredients {:_id (str uniqueID)}))
   (ok {:id (str uniqueID)})))

(defn updateFridge [ingredients id]
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        collection "fridges"]
    ; "addToSet" adds an element to a set (ingredients) and "each" takes an array and operates over each element (since :ingredients is a map to an array of {:name <name>}
    ;and I receive an array of Ingredients, I must use the "each" operator to insert each value, not the array as a whole
    (mc/update-by-id db collection id {$addToSet {:ingredients {$each ingredients}}})
    (ok)))

(defn getAllFridges []
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        collection "fridges"
      ]
    (-> (mc/find-maps db collection)
    (ok)))
  )

(defn getRecipes [id]
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        collection "fridges"
        ingredients (str "&ingredients=" (join ",+" (map #(:name %) (:ingredients (mc/find-one-as-map db collection {:_id id})))))
        baseUrl "https://api.spoonacular.com/recipes/findByIngredients?apiKey=183475a9cadf4ad28c19c441ea5e369c&number=2"
        fullUrl (str baseUrl ingredients)
        recipes (client/get fullUrl {:as :json-strict})
        ]
    (ok (:body recipes))
    )
  )