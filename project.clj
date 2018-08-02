(defproject clj-elapsed "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main elapsed.main
  :profiles {:uberjar {:omit-source    true
                       :aot            :all
                       :uberjar-name   "clj-elapsed.jar"
                       :source-paths   ["src"]}})
