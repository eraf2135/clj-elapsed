(ns elaspsed.core-test
  (:require [clojure.test :refer :all]
            [elaspsed.core :refer :all]))

(deftest elapsed-test
  (testing "only seconds diff"
    (is (= 11
          (elapsed "2018-06-25T21:53:35" "2018-06-25T21:53:46"))))

  (testing "mins and seconds diff"
    (is (= 131
           (elapsed "2018-06-25T21:51:35" "2018-06-25T21:53:46"))))

  (testing "hours, mins and seconds diff"
    (is (= 7331
           (elapsed "2018-06-25T19:51:35" "2018-06-25T21:53:46"))))

  (testing "days, hours, mins and seconds diff"
    (is (= 180131
           (elapsed "2018-06-23T19:51:35" "2018-06-25T21:53:46"))))

  (testing "31 day month, days, hours, mins and seconds diff"
    (is (= 2858531
           (elapsed "2018-05-23T19:51:35" "2018-06-25T21:53:46"))))

  (testing "30 day month, days, hours, mins and seconds diff"
    (is (= 2772131
           (elapsed "2018-04-23T19:51:35" "2018-05-25T21:53:46"))))

  (testing "28 day month, days, hours, mins and seconds diff"
    (is (= 2599331
           (elapsed "2018-02-23T19:51:35" "2018-03-25T21:53:46"))))

  (testing "multiple month, days, hours, mins and seconds diff"
    (is (= 5277731
           (elapsed "2018-02-23T19:51:35" "2018-04-25T21:53:46"))))

  (testing "years, multiple month, days, hours, mins and seconds diff"
    (is (= 65930531
           (elapsed "2016-03-23T19:51:35" "2018-04-25T21:53:46"))))

  (testing "leap year at start boundary excl feb, multiple month, days, hours, mins and seconds diff"
    (is (= 65930531
           (elapsed "2016-03-23T19:51:35" "2018-04-25T21:53:46"))))

  (testing "leap year at start boundary incl feb, multiple month, days, hours, mins and seconds diff"
    (is (= 68436131
           (elapsed "2016-02-23T19:51:35" "2018-04-25T21:53:46"))))

  (testing "leap year at end boundary excl feb, multiple month, days, hours, mins and seconds diff"
    (is (= 31716131
           (elapsed "2015-02-23T19:51:35" "2016-02-25T21:53:46"))))

  (testing "leap year at end boundary incl feb, multiple month, days, hours, mins and seconds diff"
    (is (= 36900131
           (elapsed "2015-02-23T19:51:35" "2016-04-25T21:53:46"))))

  (testing "multiple leap years in between, multiple month, days, hours, mins and seconds diff"
    (is (= 289360931
           (elapsed "2007-02-23T19:51:35" "2016-04-25T21:53:46")))))
