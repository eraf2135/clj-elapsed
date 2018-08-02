(ns elaspsed.main
  (:require [elaspsed.core :as core]
            [clojure.string :as s]))

#_(defn -main
  "Interactive prompts for date input"
  []
  (println "This application calculated the elapsed seconds between 2 dates")
  (loop []
    (println "Please enter the earlier date in the format YYYY-MM-DDTHH:MM:SS")
    (let [from-date (read-line)]
      (println "Please enter the later date in the format YYYY-MM-DDTHH:MM:SS")
      (let [to-date (read-line)
            secs (core/elapsed from-date to-date)]
        (println "Elapsed seconds are:")
        (println secs)))

    (println "Would you like to enter some more dates? (Y/N)")
    (if (= (s/upper-case (read-line)) "Y")
      (recur)
      (println "Bye!"))))

(defn -main
  "Takes from & 2 dates from std in, one date on each line."
  []
  (let [from-date (read-line)
        to-date (read-line)]
    (println (core/elapsed from-date to-date))))
