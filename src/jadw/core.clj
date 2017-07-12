(ns jadw.core
  (:gen-class)
  (:require [clj-http.client :as client]))

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
