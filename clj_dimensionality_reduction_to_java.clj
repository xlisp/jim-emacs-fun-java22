(ns dimensionality.reduction)

;; 你记忆力很差，只能记住元结构，很依赖于谷歌: 比如clojure你还需要 https://clojuredocs.org/的帮忙，不然你记不住这些长的名字。

(doseq [i (list "one", "two", "three")]
  (println i))
;; => or
(run! println (list "one", "two", "three"))

(map (fn [_] 3) (list "1" , "11" , "111"))
;; => (3 3 3)

(map-indexed (fn [i v] i) (list "one", "two", "three", "four"))
;; => (0 1 2 3)

(->>
  (list "Shenzhen", "Brussels", "Taipei", "Buenos Aires", "Sydney", "Bristol")
  (filter (fn [s] (re-find #"^B" s)))
  (map clojure.string/upper-case)
  sort
  (run! println))

(map (fn [[a b]] (+ a b)) (partition 2 1 (list 1, 2, 3, 4, 5, 6, 7, 8, 9)))
;; => (3 5 7 9 11 13 15 17)

;; Gatherers.windowSliding(2)
(partition 2 1 (list "one" , "two" , "three" , "four" , "five"))
;; => (("one" "two") ("two" "three") ("three" "four") ("four" "five"))

;; Gatherers.windowFixed(2)
;; (partition number offset list)
(partition 2 2 nil (list "one" , "two" , "three" , "four" , "five"))
;; => (("one" "two") ("three" "four") ("five"))

;; ===== defrecord
(defrecord Person [fname lname address])
(defrecord Address [street city state zip])

(def stu (Person. "Stu" "Halloway"
           (Address. "200 N Mangum"
             "Durham"
             "NC"
             27701)))

(:lname stu)

(-> stu :address :city)
;; => "Durham"

(update-in stu [:address :zip] inc)
;; => #dimensionality.reduction.Person{:fname "Stu", :lname "Halloway", :address #dimensionality.reduction.Address{:street "200 N Mangum", :city "Durham", :state "NC", :zip 27702}}

;; ========= https://clojure.org/reference/java_interop ===> (doto (new java.util.HashMap) (.put "a" 1) (.put "b" 2))
;; === implement a Java interface in defrecord
(import java.net.FileNameMap)

(defrecord Thing [a]
  FileNameMap
  (getContentTypeFor [this fileName] (str a "-" fileName)))

(def thing (Thing. "foo"))

(instance? FileNameMap thing)
;; => true

(map #(println %) (.getInterfaces Thing))
;; =>
;; java.net.FileNameMap
;; clojure.lang.IRecord
;; clojure.lang.IHashEq
;; clojure.lang.IObj
;; clojure.lang.ILookup
;; clojure.lang.IKeywordLookup
;; clojure.lang.IPersistentMap
;; java.util.Map
;; java.io.Serializable
;;

(.getContentTypeFor thing "bar")
;; => "foo-bar"
