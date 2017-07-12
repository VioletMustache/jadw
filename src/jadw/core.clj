(ns jadw.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def *token*
  "Discogs API token."
  nil)

(defn set-token
  "Set Discogs API token."
  [token]
  (def *token* token))

(def *discogs-api*
  "Discogs API URL."
  "https://api.discogs.com/database/")

(defn fetch-album
  "Returns vector of albums."
  [artist album]
  (json/read-str (:body (client/get (str *discogs-api* "search?title=" artist "-" album "&token=" *token*))) :key-fn keyword))

;; This function returns very first
;; result from the search results.
(defn get-album-id-from-json
  "Extract album ID from album."
  [albums]
  (:id (first (:results albums))))
