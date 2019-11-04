(ns my_api.core
  (:require [compojure.api.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.http-response :refer :all]
            ;[monger.core :as mg]
            ;[monger.collection :as mc]
            ;[my-api.schemas :refer [Ingredient Recipe]]))


(def app
  (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Todays Menu"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
             :tags ["api"]

             (GET "/ping" []
                  :return {:result String}
                  :summary "tests api existence"
                  (ok {:result "pong"}))


             ;(POST "/" []
             ;  :return Pizza
             ;  :body [pizza Pizza]
             ;  :summary "echoes a Pizza"
             ;  (ok pizza))

             )))


(defn -main
  [& args]
  ; ...
  (run-jetty app {:port 3001}))