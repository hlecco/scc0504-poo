#include <mycomplex.hpp>

int main() {
	MyComplex z(-1231, 139);
	MyComplex w(-42, 80);
	MyComplex u;

	u = z + w;
	u.print();

	u = z - w;
	u.print();

	u = z * w;
	u.print();

	u = MyComplex(0,1);
	u.print();
	
	cout << u.abs() << endl;
	
	return 0;
}
