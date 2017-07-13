(ns jadw.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as str]))

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

(defn get-track-name
  "Returns track name."
  [track]
  (:title track))

(defn get-track-names
  "Returns vector of track names."
  [tracks]
  (map get-track-name tracks))

;; TODO: this search should not be case sensitive
(defn song-named-after-albump
  "Returns true if there is a song named as album."
  [album tracks]
  (some #(= album %) tracks))

(defn check-if-album-has-song-with-the-same-namep
  "Return true if album has a song with the same name."
  [artist album]
  (song-named-after-albump album
                           (get-track-names
                            (get-tracklist-from-release
                             (get-release-information
                              (get-album-id
                               (find-album artist album)))))))
