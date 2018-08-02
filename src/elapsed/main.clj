(ns elapsed.main
  (:gen-class)
  (:require [elapsed.core :as core]
            [clojure.string :as s]))

(defn -main
  "Takes from & 2 dates from std in, one date on each line."
  []
  (let [from-date (read-line)
        to-date (read-line)]
    (println (core/elapsed from-date to-date))))
