import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { createFileRoute } from "@tanstack/react-router";
import { Navigation } from "lucide-react";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { useForm } from "react-hook-form";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import RestaurantList from "@/components/restaurant-list";
import { SetStateAction, useEffect, useState } from "react";
import {
  getRestaurants,
  searchNearbyRestaurants,
  searchRestaurantsByName,
} from "@/api/restaurants";
import { useQuery } from "@tanstack/react-query";

export type State = {
  page: number;
  setPage: React.Dispatch<SetStateAction<number>>;
  data: any;
};

const formSchema = z.object({ name: z.string().min(1).max(255) });

export function NameSearchForm({
  setRestaurants,
  setPage,
}: {
  setRestaurants: React.Dispatch<SetStateAction<any>>;
  setPage: React.Dispatch<SetStateAction<number>>;
}) {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
  });

  async function onSubmit(values: z.infer<typeof formSchema>) {
    setPage(1);
    const res = await searchRestaurantsByName(values.name);
    if (res) {
      setRestaurants(res);
    }
  }

  return (
    <Form {...form}>
      <form
        noValidate
        onSubmit={form.handleSubmit(onSubmit)}
        className="flex gap-4 items-center border-[1px] px-2 rounded-md"
      >
        <FormField
          control={form.control}
          name="name"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input
                  className="p-6 w-[40rem] border-none"
                  placeholder="Enter restaurant name"
                  {...field}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <Button type="submit" className="p-6">
          Search
        </Button>
      </form>
    </Form>
  );
}

const Homepage = () => {
  const [page, setPage] = useState<number>(1);
  const [restaurants, setRestaurants] = useState<any>(null);

  const { data } = useQuery({
    queryKey: ["restaurants", page],
    queryFn: () => getRestaurants(String(page)),
  });

  useEffect(() => setRestaurants(data), [data]);

  async function setLocation() {
    navigator.geolocation.getCurrentPosition(async (position) => {
      const nearbyRestaurants = await searchNearbyRestaurants(
        String(position.coords.latitude),
        String(position.coords.longitude)
      );
      if (nearbyRestaurants) {
        setRestaurants(nearbyRestaurants);
      }
    });
  }

  return (
    <div>
      <div className="flex justify-center">
        <NameSearchForm setRestaurants={setRestaurants} setPage={setPage} />
      </div>

      <div className="flex justify-center py-4">
        <p className="text-gray-400">--------- or --------</p>
      </div>

      <div className="flex justify-center">
        <Button
          onClick={setLocation}
          variant={"expandIcon"}
          Icon={Navigation}
          iconPlacement="left"
          className="p-6"
        >
          <p className="group-hover:p-4 transition-all duration-300">
            Use My Current Location
          </p>
        </Button>
      </div>

      <div className="mt-20">
        <RestaurantList page={page} setPage={setPage} data={restaurants} />
      </div>
    </div>
  );
};

export const Route = createFileRoute("/")({
  component: Homepage,
});
