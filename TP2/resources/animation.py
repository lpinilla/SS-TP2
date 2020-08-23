import matplotlib.animation as ani
import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

times = [
    {
    't' : 0,
    'x' : [],
    'y' : [],
    'v' : []
    }
]

aux = {'t': 0, 'x' : [], 'y' : [], 'v' : []}

with open('./Dynamic.txt') as f:
    next(f)
    for line in f:
        #-1 para ignorar el \n
        data = line[:-1].split('   ')
        if(len(data) == 1):
            if(len(aux['x']) != 0):
                times.append(aux)
                aux = {'t': 0, 'x' : [], 'y' : [], 'v' : []}
            aux['t'] = data[0]
        else:
            aux['x'].append(float(data[0]))
            aux['y'].append(float(data[1]))
            aux['v'].append(float(data[2]))
f.close()

times = times[1:]

fig, ax = plt.subplots()
#plt.plot(times[0]['x'][0:], times[0]['y'][0:], '.', markersize=6, label='remaining')

#plt.xlabel('x')
#plt.ylabel('y')
#plt.title('testing')
#plt.legend()
#
##cambiando el tamaño de la grilla la grilla
#ax.set_xlim(0, 100)
#ax.set_ylim(0, 100)
#ax.grid(linestyle='-', linewidth='0.5')
#ax.xaxis.set_major_locator(MultipleLocator(10))
#ax.yaxis.set_major_locator(MultipleLocator(10))
#plt.show()

def animate(i):
    plt.clf()
    plt.plot(times[i]['x'][0:], times[i]['y'][0:], '.', markersize=6, label='remaining')

Writer = ani.writers['ffmpeg']
writer = Writer(fps=300, metadata=dict(artist='me'), bitrate=1080)

fig = plt.figure(figsize=(10,6))
#plt.xlim(0, 15)
#plt.ylim(0, 100)
ax.set_xlim(0, 100)
ax.set_ylim(0, 100)
ax.grid(linestyle='-', linewidth='0.5')
ax.xaxis.set_major_locator(MultipleLocator(10))
ax.yaxis.set_major_locator(MultipleLocator(10))

animation = ani.FuncAnimation(fig, animate, frames=len(times) -1, interval=1, repeat=True)
#plt.show()
animation.save('poc.gif', writer='imagemagick')
