package me.kaimson.arialclient.gui.screen.smoothscrolling;

import me.kaimson.arialclient.Client;
import net.minecraft.client.gui.GuiSlot;

public class GuiSlotScroller implements RunSixtyTimesEverySec{
    private final GuiSlot list;

    public GuiSlotScroller(final GuiSlot list)
    {
        this.list = list;
    }

    @Override
    public void run()
    {
        if (this.list == null)
        {
            Client.info("LIST IS GONE!");
            return;
        }

        final double scrollVelocity = list.scrollVelocity;

        if (scrollVelocity == 0.0 && this.list.amountScrolled >= 0.0 && this.list.amountScrolled <= this.list.func_148135_f())
        {
            this.unregisterTick();
        }
        else
        {
            final double change = list.scrollVelocity * 0.3;

            if (list.scrollVelocity != 0.0)
            {
                final GuiSlot list = this.list;
                list.amountScrolled += change;
                final double minus = list.scrollVelocity * ((this.list.amountScrolled >= 0.0 && this.list.amountScrolled <= this.list.func_148135_f()) ? 0.2 : 0.4);
                list.scrollVelocity = (float)(list.scrollVelocity - minus);

                if (Math.abs(list.scrollVelocity) < 0.1)
                {
                    list.scrollVelocity = 0.0;
                }
            }

            if (this.list.amountScrolled < 0.0 && list.scrollVelocity == 0.0)
            {
                this.list.amountScrolled = (float) Math.min(this.list.amountScrolled + (0.0 - this.list.amountScrolled) * 0.2, 0.0);

                if (this.list.amountScrolled > -0.1 && this.list.amountScrolled < 0.0)
                {
                    this.list.amountScrolled = 0.0F;
                }
            }
            else if (this.list.amountScrolled > this.list.func_148135_f() && list.scrollVelocity == 0.0)
            {
                this.list.amountScrolled = (float) Math.max(this.list.amountScrolled - (this.list.amountScrolled - this.list.func_148135_f()) * 0.2, this.list.func_148135_f());

                if (this.list.amountScrolled > this.list.func_148135_f() && this.list.amountScrolled < this.list.func_148135_f() + 0.1)
                {
                    this.list.amountScrolled = this.list.func_148135_f();
                }
            }
        }
    }
}
