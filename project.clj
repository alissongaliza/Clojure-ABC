 (defproject todaysMenu "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
   :dependencies [[org.clojure/clojure "1.10.0"]
                  ; HTTP client
                  [clj-http "3.10.0"]
                  ; Web
                  [prismatic/schema "1.1.9"]
                  [ring/ring-jetty-adapter "1.6.3"]
                  [metosin/compojure-api "2.0.0-alpha30"]
                  ; Database
                  [com.novemberain/monger "3.1.0"]
                  ; Password Hashing
                  [buddy/buddy-hashers "1.3.0"]]
   :ring {:handler backend.handler/app
          :auto-reload? true
          :auto-refresh? true
          :reload-paths ["src"]}
   :uberjar-name "server.jar"
   :main backend.core
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.12.5"]]}})