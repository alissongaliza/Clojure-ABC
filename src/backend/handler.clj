(ns backend.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.http-response :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [backend.schemas :refer [Ingredient Recipe Fridge]])
  (:import org.bson.types.ObjectId))


(defn createFridge [ingredients]
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        uniqueID (ObjectId)
        fridgeIngredients {:ingredients ingredients}
        collection "fridges"]
   (mc/insert db collection (merge fridgeIngredients {:_id uniqueID}))
   (ok {:id uniqueID})))

(defn updateFridge [ingredients id]
  (let [uri "mongodb://alisson:Tw1!4&$jF8m@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        collection "fridges"]
    (mc/update-by-id db collection id {$addToSet {:ingredients [ingredients]}})
    (ok)))

(defn getAllFridges []
  (let [uri "mongodb://<alisson>:<Tw1!4&$jF8m>@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)
        collection "fridges"]
    (mc/find-maps db collection)
    (ok))
  )