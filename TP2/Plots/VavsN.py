# agarro va despues de un cierto t (sacado del que se estanca) con un n(entre 0 y 5, subiendo de a 0.1), con un N
# en un archivo voy a tener va's para destintos n(suben de 0.1), con un N

import matplotlib.pyplot as plt
import numpy as np

# leo de vaOut la salida de los diferentes va
# filename1 = "../OutFiles/VavsN.txt"
# filename2 = "../OutFiles/VavsN2.txt"
filename1 = "../OutFiles/vaVsEta50.txt"
filename2 = "../OutFiles/vaVsEta100.txt"

t = np.loadtxt(filename1, unpack=True)
t2 = np.loadtxt(filename2, unpack=True)

X = np.arange(0.0, 5.0, 0.5)
Y = list(range(0, len(t)))
Y2 = list(range(0, len(t2)))
E = list(range(0, len(t)))
E2 = list(range(0, len(t2)))

for i in range(0, len(t)):
    Y[i] = np.mean(t[i])
    E[i] = np.std(t[i])
for i in range(0, len(t2)):
    Y2[i] = np.mean(t2[i])
    E2[i] = np.std(t2[i])

plt.errorbar(X, Y, E, linestyle='-', marker='.')
plt.errorbar(X, Y2, E2, linestyle='-', marker='.', color='red')

plt.title("Va vs N")
plt.xlabel('N')
plt.ylabel('Va')
plt.legend(['N=', 'N='])
plt.show()
