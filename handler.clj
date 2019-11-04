(ns my-api.handler
  (:gen-class)
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.http-response :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [my-api.schemas :refer [Ingredient Recipe Fridge]]))


(defn updateFridge [ingredients]
  (let [uri "mongodb://<alisson>:<Tw1!4&$jF8m>@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)]
    ; ->> = Thread last macro
    (->> ingredients
         ;; multiple documents at once
         (mc/insert-batch db "documents")
         (ok))))

(defn createFridge [ingredients]
  (let [uri "mongodb://<alisson>:<Tw1!4&$jF8m>@ds141178.mlab.com:41178/todays-menu"
        {:keys [conn db]} (mg/connect-via-uri uri)]
    (mc/insert-and-return db "documents" Fridge {:ingredients [ingredients]})
    )
  )

