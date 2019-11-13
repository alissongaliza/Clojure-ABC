(ns backend.core
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.http-response :refer :all]
            [backend.endpoints :refer [user-routes]])
  (:gen-class))

(def swagger-config
  {:ui "/swagger"
   :spec "/swagger.json"
   :options {:ui {:validatorUrl nil}
             :data {:info {:version "1.0.0", :title "Today's Menu"}}}})

(def app
  (api {:swagger swagger-config}
    (apply routes user-routes)))


(defn -main
  [& args]
  ; ...
  (run-jetty app {:port 3001}))