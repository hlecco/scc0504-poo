#ifndef _MYCLASS_H_
#define _MYCLASS_H_

template <class T>
class MyClass {
private:
	T a;
	T b;
public:
	MyClass(T, T);
	T getMax();
	T getMin();
	T getSum();
};

template <class T>
MyClass<T>::MyClass(T pa, T pb) {
	this->a = pa;
	this->b = pb;
}

template <class T>
T MyClass<T>::getMax() {
	return (this->a > this->b ? this->a : this->b);
}

template <class T>
T MyClass<T>::getMin() {
	return (this->a > this->b ? this->b : this->a);
}

template <class T>
T MyClass<T>::getSum() {
	return (this->a) + (this->b);
}

#endif
