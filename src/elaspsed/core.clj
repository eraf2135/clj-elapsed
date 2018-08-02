(ns elaspsed.core
  (:require [clojure.string :as s]))

(defn- ->int [i]
  (Integer/parseInt i))

(defn str-datetime->ints
  "Returns a vector of component date parts as ints.
  e.g. \"2018-12-31T23:11:52\" = [2018 12 31 23 11 52]"
  [datetime-str]
  (let [[date-str time-str] (s/split datetime-str #"T")
        [year month day] (map ->int (s/split date-str #"-"))
        [hrs mins secs] (map ->int (s/split time-str #":"))]
    [year month day hrs mins secs]))

(defn- days->secs [days]
  (* 24 60 60 days))

(defn- hrs->secs [hrs]
  (* 60 60 hrs))

(defn- mins->secs [mins]
  (* 60 mins))

(def ^:private days-in-month [31 28 31 30 31 30 31 31 30 31 30 31])

(defn- months->secs [from-month to-month]
  (let [days (reduce (fn [i month]
                       (+ i (days-in-month (dec month))))
                     0
                     (range from-month to-month))]
    (days->secs days)))

(defn- leap-year?
  "A leap year is every 4 years, but not every 100 years, then again every 400 years"
  [year]
  (if (or (and (zero? (mod year 4))
               (not (zero? (mod year 100))))
          (zero? (mod year 400)))
    true
    false))

(defn leap-year-adj-days [from-year from-month to-year to-month]
  (let [num-leap-years-excl-boundaries (->> (range (inc from-year) to-year)
                                            (map leap-year?)
                                            (filter true?)
                                            count)
        from-boundary-leap-year-adj (if (and (leap-year? from-year)
                                             (<= from-month 2)
                                             (or (> to-month 2)
                                                 (> to-year from-year)))
                                      1
                                      0)
        to-boundary-leap-year-adj (if (and (leap-year? to-year)
                                           (> to-month 2)
                                           (or (<= from-month 2)
                                               (< from-year to-year)))
                                    1
                                    0)]
    (+ num-leap-years-excl-boundaries
       from-boundary-leap-year-adj
       to-boundary-leap-year-adj)))

(defn- years->secs [from-year from-month to-year to-month]
  (-> (- to-year from-year)
      (* 365)
      (+ (leap-year-adj-days from-year from-month to-year to-month))
      days->secs))

(defn elapsed
  "Assumes first date is smaller for now"
  [from-datetime-str to-datetime-str]
  (let [[from-year from-month from-day from-hrs from-mins from-secs]
        (str-datetime->ints from-datetime-str)

        [to-year to-month to-day to-hrs to-mins to-secs]
        (str-datetime->ints to-datetime-str)]

    (+ (- to-secs from-secs)
       (mins->secs (- to-mins from-mins))
       (hrs->secs (- to-hrs from-hrs))
       (days->secs (- to-day from-day))
       (months->secs from-month to-month)
       (years->secs from-year from-month to-year to-month))))
