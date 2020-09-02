# agarro va despues de un cierto t (sacado del que se estanca) con un n(entre 0 y 5, subiendo de a 0.1), con un N
# en un archivo voy a tener va's para destintos n(suben de 0.1), con un N

import matplotlib.pyplot as plt
import numpy as np

# leo de vaOut la salida de los diferentes va
# filename1 = "../OutFiles/VavsN.txt"
# filename2 = "../OutFiles/VavsN2.txt"
filename1 = "../OutFiles/vaVsDen1.txt"
filename2 = "../OutFiles/vaVsDen2.txt"
filename3 = "../OutFiles/vaVsDen3.txt"





t = np.loadtxt(filename1, unpack=True)
t2 = np.loadtxt(filename2, unpack=True)
t3 = np.loadtxt(filename3, unpack=True)

#X = list(range(1, len(t)+1))
X = np.arange(1, 11, 0.5)
Y = list(range(0, len(t)))

Y2 = list(range(0, len(t2)))
Y3 = list(range(0, len(t3)))

E = list(range(0, len(t)))
E2 = list(range(0, len(t2)))
E3 = list(range(0, len(t3)))


for i in range(0, len(t)):
    Y[i] = np.mean(t[i])
    E[i] = np.std(t[i])

for i in range(0, len(t2)):
    Y2[i] = np.mean(t2[i])
    E2[i] = np.std(t2[i])

for i in range(0, len(t3)):
    Y3[i] = np.mean(t3[i])
    E3[i] = np.std(t3[i])

plt.errorbar(X, Y, E, linestyle='-', marker='.')
plt.errorbar(X, Y2, E2, linestyle='-', marker='.', color='red')
plt.errorbar(X, Y3, E3, linestyle='-', marker='.', color='green')


plt.title("Va vs Densidad")
plt.xlabel('Densidad')
plt.ylabel('Va')
plt.legend(['Ruido=1','Ruido=2','Ruido=4'])
#plt.legend(['N=100   L=3', 'N=100 L=5','N=400 L=10'])
plt.rc('grid', linestyle="-", color='black')
plt.grid(True)
plt.show()
