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


;; Gatherers.windowSliding(2)
(partition 2 1 (list "one" , "two" , "three" , "four" , "five"))
;; => (("one" "two") ("two" "three") ("three" "four") ("four" "five"))

;; Gatherers.windowFixed(2)
;; (partition number offset list)
(partition 2 2 nil (list "one" , "two" , "three" , "four" , "five"))
;; => (("one" "two") ("three" "four") ("five"))
