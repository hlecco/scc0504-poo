cat args.txt | while read args; do
    echo $args
    echo "$args $(java -cp target/ex4-1.0.jar ex4.Main $args)" >> runs.txt
done
