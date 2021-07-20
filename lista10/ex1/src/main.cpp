#include <iostream>
#include <myclass.hpp>
using namespace std;

int main() {
	MyClass<int> mc (1, 2);

	cout << mc.getSum() << endl;
	cout << mc.getMax() << endl;
	cout << mc.getMin() << endl;
	
	return 0;
}
