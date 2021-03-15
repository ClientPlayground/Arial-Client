package me.kaimson.arialclient.event;

import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventHandler
{
    private static final Map<Class<? extends Event>, List<MethodData >> bus = Maps.newHashMap();

    public static void register(Object o)
    {
        for (final Method method : o.getClass().getDeclaredMethods())
        {
            if (method.getParameterTypes().length != 1 || !(method.isAnnotationPresent(TypeEvent.class)))
            {
                continue;
            }

            register(method, o);
        }
    }

    private static void register(Method m, Object s)
    {
        Class <? extends Event> c = (Class<? extends Event>) m.getParameterTypes()[0];
        MethodData d = new MethodData(s, m);

        if (bus.containsKey(c))
        {
            if (!bus.get(c).contains(d))
            {
                bus.get(c).add(d);
            }
        }
        else
        {
            bus.put(c, new CopyOnWriteArrayList<MethodData>()
            {
                private static final long serialVersionUID = 666L;
                {
                    add(d);
                }
            });
        }
    }

    public static void unregister(Object object)
    {
        for (final List<MethodData> dataList : bus.values())
        {
            dataList.removeIf(data -> data.getSource().equals(object));
        }
    }

    public static void unregister(Object object, Class <? extends Event > eventClass)
    {
        if (bus.containsKey(eventClass))
        {
            bus.get(eventClass).removeIf(data -> data.getSource().equals(object));
        }
    }

    public static boolean call(final Event e)
    {
        List<MethodData> list = bus.get(e.getClass());

        if (list != null)
        {
            for (final MethodData m : list)
            {
                invoke(m, e);
            }
        }
        return (e instanceof Cancellable && ((Cancellable)e).isCancelled());
    }

    private static void invoke(MethodData data, Event argument)
    {
        try
        {
            data.getTarget().setAccessible(true);
            data.getTarget().invoke(data.getSource(), argument);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    private static final class MethodData
    {
        Object source;
        Method target;

        public MethodData(Object source, Method target)
        {
            this.source = source;
            this.target = target;
        }

        public Object getSource()
        {
            return this.source;
        }

        public Method getTarget()
        {
            return this.target;
        }
    }
}
