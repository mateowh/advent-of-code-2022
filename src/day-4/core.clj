(defn process-pair [pair-string]
  ((comp
    #(map read-string %)
    #(clojure.string/split % #",|-"))
   pair-string))

(def input-data
  (as->
   "src/day-4/input.txt" input
    (slurp input)
    (clojure.string/split input #"\n")
    (map process-pair input)))

(defmulti overlap? (fn [_ _ overlap-type] overlap-type))

;; part 1
(defmethod overlap? "full" [set-1 set-2 _]
  (or (clojure.set/superset? set-1 set-2) (clojure.set/superset? set-2 set-1)))

;; part 2
(defmethod overlap? "partial" [set-1 set-2 _]
  (not (empty? (clojure.set/intersection set-1 set-2))))

(defn process-input-data [pair-assignment overlap-type]
  (let [set-1 (set (range (nth pair-assignment 0) (inc (nth pair-assignment 1))))
        set-2 (set (range (nth pair-assignment 2) (inc (nth pair-assignment 3))))]
    (overlap? set-1 set-2 overlap-type)))

(defn calculate-overlaps [overlap-type]
  (->> input-data
       (map #(process-input-data % overlap-type))
       (filter identity)
       count))