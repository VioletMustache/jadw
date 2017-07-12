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
  "https://api.discogs.com/")

(defn find-album
  "Returns vector of albums."
  [artist album]
  (json/read-str (:body (client/get (str *discogs-api*
                                         "database/search?release_title=" album
                                         "&artist=" artist
                                         "&per_page=3?page=1"
                                         "&token=" *token*))) :key-fn keyword))

(defn get-album-id
  "Extract album ID from first album in vector of albums (json)."
  [albums]
  (:id (first (:results albums))))

(defn get-release-information
  "Returns given release data."
  [releaseid]
  (json/read-str (:body (client/get (str *discogs-api* "releases/" releaseid))) :key-fn keyword))

(defn get-tracklist-from-release
  "Returns list of tracks from release json data."
  [release]
  (:tracklist release))
