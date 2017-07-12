(defproject jadw "0.1.0-SNAPSHOT"
  :description "Just Another Discogs Wrapper"
  :url "https://github.com/VioletMustache/jadw"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.6.1"]]
  :main ^:skip-aot jadw.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
